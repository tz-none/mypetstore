package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DButil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String GET_PRODUCT_LIST = "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY=?";
    private static final String GET_PRODUCT = "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID=?";
    private static final String SEARCH_PRODUCT = "select PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId from PRODUCT WHERE LOWER(NAME) like ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<Product>();
        try{
            Connection connection = DButil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST);
            preparedStatement.setString(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DButil.closeResultSet(resultSet);
            DButil.closePreparedStatement(preparedStatement);
            DButil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try{
            Connection connection = DButil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DButil.closeResultSet(resultSet);
            DButil.closePreparedStatement(preparedStatement);
            DButil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try {
            try{
                Connection connection = DButil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT);
                preparedStatement.setString(1, keywords);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getString(1));
                    product.setName(resultSet.getString(2));
                    product.setDescription(resultSet.getString(3));
                    product.setCategoryId(resultSet.getString(4));
                    productList.add(product);
                }
                DButil.closeResultSet(resultSet);
                DButil.closePreparedStatement(preparedStatement);
                DButil.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
