package com.devland.assignment.assignment12.reply;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.assignment12.reply.model.Reply;
import com.devland.assignment.assignment12.reply.model.dto.ReplyRequestDTO;
import com.devland.assignment.assignment12.reply.model.dto.ReplyResponseDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping
    public ResponseEntity<Page<ReplyResponseDTO>> getAll(
            @RequestParam("reply") Optional<String> optionalReply,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Reply> pageReply = this.replyService.getAll(optionalReply, pageable);
        Page<ReplyResponseDTO> replyResponseDTOs = pageReply.map(Reply::convertToResponse);

        return ResponseEntity.ok(replyResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponseDTO> getOne(@PathVariable("id") Long id) {
        Reply existingReply = this.replyService.getOne(id);
        ReplyResponseDTO replyResponseDTO = existingReply.convertToResponse();

        return ResponseEntity.ok(replyResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ReplyResponseDTO> create(@RequestBody @Valid ReplyRequestDTO replyRequestDTO) {
        Reply newReply = replyRequestDTO.convertToEntity();

        Reply savedReply = this.replyService.create(newReply);
        ReplyResponseDTO replyResponseDTO = savedReply.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(replyResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReplyResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody ReplyRequestDTO replyRequestDTO) {
        Reply updatedReply = replyRequestDTO.convertToEntity();
        updatedReply.setId(id);
        Reply savedReply = this.replyService.update(updatedReply);
        ReplyResponseDTO replyResponseDTO = savedReply.convertToResponse();

        return ResponseEntity.ok(replyResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.replyService.delete(id);

        return ResponseEntity.ok().build();
    }
}
