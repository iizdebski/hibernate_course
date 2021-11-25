CREATE TABLE `backup_employee_table` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `bonus` decimal(19,2) DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `date_of_joining` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `employee_name` varchar(60) NOT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UK_2casspobvavvi9s23312f9mhm` (`email`)
) ;

INSERT INTO `backup_employee_table`
(`bonus`,
`designation`,
`date_of_joining`,
`email`,
`employee_name`,
`salary`)
VALUES(1500,'Tester','2017-10-21 10:30:49','barry.j1920@infotech.com','barry Johnson',50000.00),
(1000,'HR','2017-10-22 10:30:49','marry.j1920@infotech.com','Marry Johnson',60000.00);
