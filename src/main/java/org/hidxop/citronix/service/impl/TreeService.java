package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.domain.entitiy.Tree;
import org.hidxop.citronix.dto.tree.*;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
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
    private final TreeValidator treeValidator;

    private final FieldService fieldService;



    public Tree getTreeById(UUID id){
        return treeRepository.findById(id).orElseThrow(()->new NotFoundException("Tree Not Found."));
    }


    @Override
    public List<TreeBasicResponseDto> findAll() {
        return treeMapper.toBasicDto(treeRepository.findAll());
    }

    @Override
    public TreeDetailedResponseDto findById(UUID uuid) {
        Tree tree=treeRepository.findById(uuid).orElseThrow(()->new NotFoundException("Tree Not Found."));
        return treeMapper.toDetailedDto(tree);
    }

    @Override
    @Transactional
    public TreeBasicResponseDto save(TreeCreateRequestDto request) {
        Field field = fieldService.getFieldById(request.fieldId());
        Tree tree=treeMapper.toEntity(request);
        tree.setField(field);
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
            Field newField = fieldService.getFieldById(treeUpdateRequestDto.fieldId());
            existTree.setField(newField);
        }

        treeValidator.validateTreeUpdate(existTree);
        treeMapper.partialUpdate(treeUpdateRequestDto, existTree);
        treeRepository.save(existTree);
        return treeMapper.toBasicDto(existTree);
    }
}
