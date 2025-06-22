package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.InventoryStockList;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "technician_inventories")
@Getter
public class TechnicianInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private TechnicianId technicianId;

    @Embedded
    private final InventoryStockList stockList;

    public TechnicianInventory() {
        this.stockList = new InventoryStockList();
    }

    public TechnicianInventory(TechnicianId technicianId) {
        this();
        this.technicianId = technicianId;
    }

    public void addToStock(Component component, int quantity, int threshold) {
        this.stockList.addItem(this, component, quantity, threshold);
    }

    public List<ComponentStock> getComponentStocks() {
        return this.stockList.getItems();
    }

}
