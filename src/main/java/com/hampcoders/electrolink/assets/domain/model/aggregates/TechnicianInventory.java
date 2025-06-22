package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.InventoryStockList;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public TechnicianInventory(CreateTechnicianInventoryCommand command) {
        this();
        this.technicianId = command.technicianId();
    }

    public void addToStock(Component component, int quantity, int threshold) {
        this.stockList.addItem(this, component, quantity, threshold);
    }

    public List<ComponentStock> getComponentStocks() {
        return this.stockList.getItems();
    }
    public boolean updateStockItem(UpdateComponentStockCommand command) {
        Optional<ComponentStock> stockToUpdate = this.stockList.getItems().stream()
                .filter(stock -> stock.getComponent().getComponentId().equals(command.componentId()))
                .findFirst();

        if (stockToUpdate.isEmpty()) {
            return false;
        }

        ComponentStock stock = stockToUpdate.get();
        stock.updateQuantity(command.newQuantity());
        stock.updateAlertThreshold(command.newAlertThreshold());
        stock.updateLastUpdated(new Date());

        return true;
    }

    public boolean removeStockItem(DeleteComponentStockCommand command) {
        return this.stockList.getItems().removeIf(stock -> stock.getComponent().getComponentId().equals(command.componentId()));
    }
}
