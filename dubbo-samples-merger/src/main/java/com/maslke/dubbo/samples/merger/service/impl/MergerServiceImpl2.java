package com.maslke.dubbo.samples.merger.service.impl;

import com.google.common.collect.Lists;
import com.maslke.dubbo.samples.merger.api.MergerService;

import java.util.List;

public class MergerServiceImpl2 implements MergerService {
    @Override
    public List<String> merge() {
        return Lists.newArrayList("v2");
    }
}
