/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  carlos
 * Created: 17/12/2022
 */

DROP TABLE IF EXISTS my_user_role;
DROP TABLE IF EXISTS my_user;

CREATE TABLE `my_user` (
  `user_name` varchar(30) NOT NULL,
  `user_pass` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_name`)
);

insert  into `my_user`(`user_name`,`user_pass`,`user_email`,`enable`) values ('admin','$2a$10$dl8TemMlPH7Z/mpBurCX8O4lu0FoWbXnhsHTYXVsmgXyzagn..8rK','c',1);


CREATE TABLE `my_user_role` (
  `user_name` varchar(30) NOT NULL,
  `user_role` varchar(15) NOT NULL,
  FOREIGN KEY (`user_name`) REFERENCES `my_user` (`user_name`)
);

insert  into `my_user_role`(`user_name`,`user_role`) values ('admin','ROLE_ADMIN');

CREATE TABLE `anuncios`(
`aid` identity PRIMARY KEY,
`tipo` varchar(10) NOT NULL,
`estado` varchar(10) NOT NULL,
`tipo_alojamento` varchar(10) NOT NULL,
`genero` varchar(15) NOT NULL,
`preco` integer,
`detalhes` varchar(500),
`user_name` varchar(30),
`contacto` integer,
`zona` varchar(50),
`data` DATE NOT NULL,
FOREIGN KEY (`user_name`) REFERENCES `my_user` (`user_name`)
);

CREATE TABLE `mensagens`(
`user_name` varchar(30),
`aid` integer,
`nome` varchar(30),
`mensagem` varchar(500),
FOREIGN KEY (`user_name`) REFERENCES `my_user` (`user_name`)
);

    

