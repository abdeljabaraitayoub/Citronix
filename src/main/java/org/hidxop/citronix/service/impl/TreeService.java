package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.domain.entitiy.Tree;
import org.hidxop.citronix.dto.tree.*;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.FieldRepository;
import org.hidxop.citronix.repository.TreeRepository;
import org.hidxop.citronix.service.ITreeService;
import org.hidxop.citronix.service.validator.TreeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TreeService implements ITreeService {


    private final TreeRepository treeRepository;
    private final TreeMapper treeMapper;

    private final FieldRepository fieldRepository;
    private final TreeValidator treeValidator;

    @Override
    public List<TreeBasicResponseDto> findAll() {
        return treeMapper.toBasicDto(treeRepository.findAll());
    }

    @Override
    public TreeDetailedResponseDto findById(UUID uuid) {
        Tree tree=treeRepository.findById(uuid).orElseThrow(()->new NotFoundException("Tree Not Found."));
        return treeMapper.toDetailedDto(tree);
    }

    @Transactional
    @Override
    public TreeBasicResponseDto save(TreeCreateRequestDto treeCreateRequestDto) {
        Tree tree=treeMapper.toEntity(treeCreateRequestDto);
        treeValidator.validateTreeCreation(tree);
        treeRepository.save(tree);
        return treeMapper.toBasicDto(tree);
    }


    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        Tree tree=treeRepository.findById(uuid).orElseThrow(()->new NotFoundException("Tree Not Found."));
        treeRepository.delete(tree);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return treeRepository.existsById(uuid);
    }

    @Override
    @Transactional
    public TreeBasicResponseDto update(UUID uuid, TreeUpdateRequestDto treeUpdateRequestDto) {
        Tree existTree = treeRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Tree Not Found"));

        if (treeUpdateRequestDto.fieldId() != null) {
            Field newField = fieldRepository.findById(treeUpdateRequestDto.fieldId())
                    .orElseThrow(() -> new NotFoundException("Field Not Found"));
            existTree.setField(newField);
        }

        treeMapper.partialUpdate(treeUpdateRequestDto, existTree);
        treeValidator.validateTreeUpdate(existTree);
        treeRepository.save(existTree);
        return treeMapper.toBasicDto(existTree);
    }
}
