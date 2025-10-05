package com.github.marcoantoniolobo.usercrud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcoantoniolobo.usercrud.dto.CreateUserRequest;
import com.github.marcoantoniolobo.usercrud.dto.UpdateUserRequest;
import com.github.marcoantoniolobo.usercrud.dto.UserDTO;
import com.github.marcoantoniolobo.usercrud.exception.ResourceNotFoundException;
import com.github.marcoantoniolobo.usercrud.kafka.UserEvent;
import com.github.marcoantoniolobo.usercrud.kafka.UserEventProducer;
import com.github.marcoantoniolobo.usercrud.model.User;
import com.github.marcoantoniolobo.usercrud.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final UserEventProducer producer;

    public UserServiceImpl(UserRepository repo, UserEventProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }

    private UserDTO toDto(User u) {
        return new UserDTO(u.getId(), u.getName(), u.getEmail());
    }

    private void sendEvent(User u, String type) {
        producer.publishEvent(new UserEvent(u.getId(), u.getName(), type));
    }

    @Override
    public UserDTO create(CreateUserRequest req) {
        User u = new User(req.getName(), req.getEmail());
        User saved = repo.save(u);
        sendEvent(saved, "CREATED");
        return toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDto(u);
    }

    @Override
    public UserDTO update(Long id, UpdateUserRequest req) {
        User u = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        User updated = repo.save(u);
        sendEvent(updated, "UPDATED");
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        repo.delete(u);
        sendEvent(u, "DELETED");
    }
}
