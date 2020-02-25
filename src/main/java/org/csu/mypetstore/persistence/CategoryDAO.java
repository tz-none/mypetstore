package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;
import java.util.List;

public interface CategoryDAO {
    //获取所有的大类
    List<Category> getCategoryList();

    //通过ID获取某一个类别
    Category getCategory(String categoryId);
}
