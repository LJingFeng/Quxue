package com.example.jinfeng.quxue.DBconn;

/**
 * Created by alienware on 2017/8/22.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {
    Connection conn=null;
    Statement pre=null;
    ResultSet rs=null;

    public Connection getConn(String dbname){//返回Connection数据库连接对象
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){}

        try {
            //----------------------------------get connection-------
            String uri = "jdbc:mysql://112.74.213.69:3306/" + dbname + "";
            String user = "root";
            String pwd = "971204";
            conn = DriverManager.getConnection(uri, user, pwd);//get connection
        }catch(SQLException e1){}
        return conn;
    }

    public ResultSet doQuery(String sql,String dbname){//查询，只执行select
        try {
            pre=this.getConn(dbname).prepareStatement(sql);
            rs=pre.executeQuery(sql);
        } catch (SQLException e) {}
        return rs;
    }

    public void doWriteDB(String sql,String dbname){//处理select其它sql语句
        try {
            pre=this.getConn(dbname).prepareStatement(sql);

            pre.execute(sql);


        } catch (SQLException e) {}
    }

    public void close(){//关闭并清空所有对象
        try {
            if(rs!=null){
                rs.close();
                rs=null;
            }
            if(pre!=null){
                pre.close();
                pre=null;
            }
            if(conn!=null){
                conn.close();
                conn=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*调用示例：
                String str="";
                new Thread(new Runnable() {

                    @Override

                    public void run() {
                        try {
                            String sql = "select*from uuuu";
                            DBconnection con = new DBconnection();
                            ResultSet rs=null;
                            String str="";
                            rs=con.doQuery(sql,"quxue");
                            //上面和之前一样
                            rs.next();//指针移到第一行，初始是在第一行的前面，如果查询结果有多行，那么这样：while(rs.next()){}
                            str+= rs.getString(1);
                            Looper.prepare();
                            Toast.makeText(Login.this,str,Toast.LENGTH_LONG).show();
                            Looper.loop();
                            con.close();
                        }catch(Exception e){
                            Toast.makeText(Login.this,e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                }).start();*/