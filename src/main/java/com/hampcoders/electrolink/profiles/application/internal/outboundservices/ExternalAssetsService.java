package com.hampcoders.electrolink.profiles.application.internal.outboundservices;

import com.hampcoders.electrolink.assets.interfaces.acl.InventoryContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalAssetsService {

    private final InventoryContextFacade inventoryContextFacade;

    public ExternalAssetsService(InventoryContextFacade inventoryContextFacade) {
        this.inventoryContextFacade = inventoryContextFacade;
    }

    /**
     * Solicita la creación de un inventario para un técnico, solo si no existe.
     * Este método traduce la necesidad del BC de Profiles a una acción concreta en el BC de Assets,
     * utilizando la fachada (ACL).
     * @param technicianProfileId El ID del Perfil del técnico.
     */
    public void createInventoryForTechnician(Long technicianProfileId) {
        // La lógica de negocio (verificar si existe) pertenece a este servicio adaptador.
        if (!inventoryContextFacade.existsInventoryForTechnician(technicianProfileId)) {
            inventoryContextFacade.createInventoryForTechnician(technicianProfileId);
        }
    }
}