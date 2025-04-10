package com.cengiz.natsjava.modules.kys.service.impl;

import com.cengiz.natsjava.modules.kys.repository.RolYetkiRepository;
import com.cengiz.natsjava.modules.kys.service.RolYetkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolYetkiServiceImpl implements RolYetkiService {

    private final RolYetkiRepository rolYetkiRepository;

}
