package com.hampcoders.electrolink.assets.domain.model.valueobjects;


import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ToString
@Embeddable
public class StockItem {

    @Getter
    @OneToMany(mappedBy="stockItems", cascade = CascadeType.ALL)
    private List<ComponentStock> stockItems;


    public StockItem() {
        this.stockItems = new ArrayList<>();
    }

    public void addItem(TechnicianInventory technicianInventory, Component component, int quantityAvailable, int alertThreshold, Date lastUpdated) {
        if (component != null) {
            this.stockItems.add(new ComponentStock(technicianInventory,component,quantityAvailable, alertThreshold,lastUpdated));
        }
    }

}
