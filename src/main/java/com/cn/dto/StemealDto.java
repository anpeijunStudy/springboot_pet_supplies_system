package com.cn.dto;

import com.cn.entity.Setmeal;
import com.cn.entity.SetmealSupplies;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 安珮军
 * @version 1.0
 * @Description:
 */
@Data
public class StemealDto extends Setmeal {

    private List<SetmealSupplies> setmealDishes = new ArrayList<>();

    private String categoryName;
}
