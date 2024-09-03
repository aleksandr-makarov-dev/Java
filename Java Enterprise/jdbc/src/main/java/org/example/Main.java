package org.example;

import org.example.dao.TicketDao;
import org.example.models.Airport;
import org.example.utils.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try{
            var list = TicketDao.getInstance().findAll();

            for(var item:list){
                System.out.println(item);
            }
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}