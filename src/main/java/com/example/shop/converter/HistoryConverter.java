package com.example.shop.converter;

import com.example.shop.model.dto.HistoryDto;
import com.example.shop.model.dto.ProductDto;
import com.example.shop.model.enity.History;
import com.example.shop.model.enity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryConverter {
    public HistoryDto toDTO(History history) {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setCategory(history.getCategory());
        historyDto.setNameProduct(history.getNameProduct());
        historyDto.setFabricator(history.getFabricator());
        historyDto.setProductCode(history.getProductCode());
        historyDto.setPurchaseDate(history.getPurchaseDate());
        return historyDto;
    }

    public List<HistoryDto> toDTOList(List<History> historyList) {
        return historyList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
