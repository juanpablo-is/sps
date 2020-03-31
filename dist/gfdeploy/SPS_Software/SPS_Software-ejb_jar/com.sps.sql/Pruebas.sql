/**
 * Author:  Juan Pablo
 * Created: 29/03/2020
 */


-- SELECT c.CUPOS - (SELECT COUNT(*) FROM Reserva r WHERE r.ID_CLIENTE = c.ID) FROM Cliente c;

SELECT COUNT(r.ID), r.ID_CLIENTE FROM Reserva AS r GROUP BY r.ID_CLIENTE;

-- SELECT r.ID_CLIENTE, COUNT(*) FROM Reserva r GROUP BY r.ID_CLIENTE;