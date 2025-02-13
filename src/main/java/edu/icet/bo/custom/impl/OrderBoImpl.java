package edu.icet.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.bo.custom.OrderBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.OrderDaoImpl;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.util.DaoType;

public class OrderBoImpl implements OrderBo {
    OrderDaoImpl orderDaoImpl = DaoFactory.getInstance().getDao(DaoType.ORDER);
    public boolean saveOrder(Order order) {
        return new OrderDaoImpl().save(new ObjectMapper().convertValue(order, OrderEntity.class));
    }
}
