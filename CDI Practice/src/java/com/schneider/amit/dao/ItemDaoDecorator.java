package com.schneider.amit.dao;

import com.google.common.base.Preconditions;
import com.google.common.collect.Constraint;
import com.google.common.collect.Constraints;
import com.google.common.collect.Lists;
import com.schneider.amit.item.Item;
import java.util.List;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 *
 * @author SESA249907
 */
@Decorator
public abstract class ItemDaoDecorator implements ItemDao {
    
    @Inject
    @Delegate
    ItemDao dao;

   public List<Item> fetchItems(){
      List<Item> list = Lists.newArrayList();
       Constraint<Item> constraint = new Constraint<Item>() {

          @Override
          public Item checkElement(Item e) {
              Preconditions.checkNotNull(e);
              Preconditions.checkArgument(e.getLimit() % 2 == 0);
              return e;
          }
      };
       Constraints.constrainedList(list, constraint);
       list.add(new Item(100, 200));
       list.add(new Item(200, 300));
       return list;
   }
    
}
