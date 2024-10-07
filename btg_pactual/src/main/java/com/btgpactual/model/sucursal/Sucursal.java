package com.btgpactual.model.sucursal;
import com.btgpactual.model.clientes.Cliente;
import com.btgpactual.model.producto.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sucursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String ciudad;

    @ManyToMany(mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<Cliente> clientes;

    @ManyToMany(mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<Producto> productos;

}
