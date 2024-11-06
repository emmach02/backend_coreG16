create table NotificacionZonaRestringida (
    id_notificacion integer not null,
    id_prueba integer not null,
    texto varchar(64) not null,
    primary key (id_notificacion),
    foreign key (id_prueba) references Pruebas(id)
);

create table NotificacionPromocion(
      id_not_promocion integer not null,
      id_modelo integer not null,
      id_interesado integer not null,
      desc varchar(64) not null,
      primary key (id_not_promocion),
      foreign key (id_modelo) references Modelos(id),
      foreign key (id_interesado) references Interesados(id)
)
