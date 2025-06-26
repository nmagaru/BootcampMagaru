package org.yearup.data;

import org.yearup.models.Order;
import org.yearup.models.Profile;

public interface OrderDao {
    Order getByUserId(int id);
    Order create(int id, Profile profile);
}
