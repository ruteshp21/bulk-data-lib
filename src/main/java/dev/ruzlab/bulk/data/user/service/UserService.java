package dev.ruzlab.bulk.data.user.service;

import dev.ruzlab.bulk.data.user.assembler.UserAssembler;
import dev.ruzlab.bulk.data.user.dao.Status;
import dev.ruzlab.bulk.data.user.dao.UserDTO;
import dev.ruzlab.bulk.data.user.dao.UserWrapper;
import dev.ruzlab.bulk.data.user.entity.UserEntity;
import dev.ruzlab.bulk.data.user.repository.UserRepository;
import dev.ruzlab.bulk.data.user.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final Integer commitSize;

    public UserService(@Value("${bulk.data.commitSize}") Integer commitSize,
                       UserRepository userRepository) {
        this.userRepository = userRepository;
        this.commitSize = commitSize;
    }

    public List<UserWrapper> processUserRecords(List<UserDTO> userDTOList) {
        return UserUtil.get(userDTOList, commitSize).stream()
                .map(this::processUserRecordChunk)
                .flatMap(Collection::stream)
                .toList();
    }

    @Transactional
    private List<UserWrapper> processUserRecordChunk(List<UserDTO> userDTOChunk) {
        return userDTOChunk.parallelStream()
                .map(UserAssembler::userDtoToEntity)
                .map(userEntity -> {
                    UserWrapper userWrapper = new UserWrapper();
                    userWrapper.setStatus(saveUserRecord(userEntity));
                    userWrapper.setUserDTO(UserAssembler.userEntityToDto(userEntity));
                    return userWrapper;
                })
                .toList();
    }

    private Status saveUserRecord(UserEntity userEntity) {
        try {
            userEntity = userRepository.save(userEntity);
            return Status.SUCCESS;
        } catch (Exception e) {
            log.error("Error while saving record", e);
        }
        return Status.FAILURE;
    }

}
