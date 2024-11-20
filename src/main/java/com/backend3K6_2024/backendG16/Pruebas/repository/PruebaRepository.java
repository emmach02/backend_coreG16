package com.backend3K6_2024.backendG16.Pruebas.repository;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Vector;

@Repository
public interface PruebaRepository extends  JpaRepository<Prueba, Integer>{
    List<Prueba> findPruebasByVehiculo(Vehiculo vehiculo);
    List<Prueba> findByFechaHoraFinIsNull();
    Prueba findByVehiculoAndFechaHoraFinIsNull(Vehiculo vehiculo);
    List<Prueba> findByInfraccionTrue();
    List<Prueba> findPruebasByInteresado(Interesado interesado);
    List<Prueba> findPruebasByEmpleado(Empleado empleado);
}

