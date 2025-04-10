package com.cengiz.natsjava.modules.genel.controller;


import com.cengiz.natsjava.base.dto.wrapper.BooleanWrapper;
import com.cengiz.natsjava.config.domain.ResponseHelper;
import com.cengiz.natsjava.config.domain.RestResponse;
import com.cengiz.natsjava.modules.genel.data.dto.MesajDto;
import com.cengiz.natsjava.modules.genel.service.MesajService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cengiz ÖZDEMİR
 * @created 10/04/2025 - 22:08
 */

@RestController
@RequestMapping("/mesajlar")
@RequiredArgsConstructor
public class MesajController  {

  private final MesajService mesajService;

  @GetMapping("/{id}")
  public ResponseEntity<RestResponse<MesajDto>> getById(@PathVariable(value = "id") @NotNull Long id) {
    return ResponseHelper.responseEntityOkFromData(mesajService.getById(id));
  }


  @GetMapping
  public ResponseEntity<RestResponse<MesajDto>> getAll() {
    return ResponseHelper.responseEntityOkFromListData(mesajService.getAll());
  }

  @PostMapping
  public ResponseEntity<RestResponse<MesajDto>> save(@Valid @RequestBody MesajDto data) {
    return ResponseHelper.responseEntityOkFromData(mesajService.save(data));
  }


  @PutMapping("/{id}")
  public ResponseEntity<RestResponse<MesajDto>> update(
      @PathVariable(value = "id") @NotNull Long id, @Valid @RequestBody MesajDto data) {
    return ResponseHelper.responseEntityOkFromData(mesajService.update(id, data));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<RestResponse<BooleanWrapper>> delete(Long id, MesajDto data) {
    return ResponseHelper.responseEntityOkFromData(mesajService.delete(id, data));
  }
}
