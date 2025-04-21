package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceBottomVo {
    private List<SpaceBottomItem> spaceBottomItemlist;
}
