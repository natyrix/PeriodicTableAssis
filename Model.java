package sample;

import java.sql.*;

public class Model{
    private static Connection connection=SqliteConnection.Connector();

    public String getName(String idd) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query="SELECT * FROM elements WHERE id = ?";

        try {
            preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,idd);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return resultSet.getString(3);
            }
            else {
                return null;
            }
        }catch (Exception e){
            return null;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }
    public String [] getInfo(String idd) throws SQLException {
        String [] infos = new String[9];
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query="SELECT * FROM elements WHERE id = ?";

        try {
            preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,idd);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                infos[0] = resultSet.getString(1);
                infos[1] = resultSet.getString(2);
                infos[2] = resultSet.getString(3);
                infos[3] = resultSet.getString(4);
                infos[4] = resultSet.getString(5);
                infos[5] = resultSet.getString(6);
                infos[6] = resultSet.getString(7);
                infos[7] = resultSet.getString(8);
                infos[8] = resultSet.getString(9);
                return infos;
            }
            else {
                return null;
            }
        }catch (Exception e){
            return null;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }

    public String [] getinfoGroup(String x) throws SQLException {
        String [] infos = new String[2];
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query="SELECT * FROM groups WHERE group_name = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet= preparedStatement.executeQuery();

            if (resultSet.next()){
                infos[0]=resultSet.getString(1);
                infos[1]=resultSet.getString(2);
                return infos;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }
    public String [] getPlaylist(String x){
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM video WHERE ID = ?";
        String[] playlist=new String[10];
        int i=1;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();


            while (resultSet.next()){
                playlist[i]=resultSet.getString(3);
                i++;
            }
            playlist[0]=String.valueOf(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(i==1){
            return null;
        }
        return playlist;
    }
    public String[] getLinks(String x){
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM video WHERE ID = ?";
        String[] links=new String[10];
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            int i=1;

            while (resultSet.next()){
                links[i]=resultSet.getString(4);
                i++;
            }
            links[0]=String.valueOf(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }
    public String [] getBookLinks(String x){
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM book WHERE ID = ?";
        String[] links=new String[10];
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            int i=1;

            while (resultSet.next()){
                links[i]=resultSet.getString(4);
                i++;
            }
            links[0]=String.valueOf(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }
    public String [] getBookList(String x){
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM book WHERE ID = ?";
        String[] links=new String[10];
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            int i=1;

            while (resultSet.next()){
                links[i]=resultSet.getString(3);
                i++;
            }
            links[0]=String.valueOf(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }
    public synchronized String getElecConfig(String x) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet= null;
        String query="SELECT * FROM elements WHERE id=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }
    public int checkExist(String x) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet= null;
        String query="SELECT * FROM ions WHERE id=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return 0;
    }
    public String getId(String x) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet= null;
        String query="SELECT * FROM elements WHERE name=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;
    }
    public String [] getElements() throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM elements ORDER BY atomic_number ASC";
        String []x= new String [116];
        try {
            preparedStatement= connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()){
                x[i]=resultSet.getString(3);
                i++;
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
            resultSet.close();

        }
        return null;
    }
    public String []  fillPossible(String x) throws SQLException {
        PreparedStatement preparedStatement=null;
        PreparedStatement preparedStatement1=null;
        ResultSet resultSet=null;
        ResultSet resultSet1=null;

        int i=0;
        String [] possiblilites=new String[116];
        String query="SELECT * FROM ions WHERE id =?";
        String query1="SELECT * FROM ions";
        try {
            preparedStatement1=connection.prepareStatement(query1);
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                resultSet1=preparedStatement1.executeQuery();
                while (resultSet1.next()){
                    possiblilites[i]=getName(resultSet1.getString(1));
                    i++;
                }
                return  possiblilites;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            preparedStatement1.close();
            resultSet.close();
            resultSet1.close();
        }
        return null;
    }
    public int getIonn(String x) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM ions WHERE id=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
        return 0;
    }
    public boolean checkDiatomic(String x) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM diatomics WHERE id=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,x);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

        return false;
    }
    public void addIons() throws SQLException {
        PreparedStatement preparedStatement=null;
        PreparedStatement preparedStatement1=null;
        ResultSet resultSet=null;
        String query="INSERT INTO ions VALUES(?,?)";
        String query1="SELECT * FROM elements";
        try {
            preparedStatement= connection.prepareStatement(query1);
            preparedStatement1=connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                preparedStatement1.setString(1,resultSet.getString(1));
                preparedStatement1.setInt(2,getIon(resultSet.getString(6)));
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            preparedStatement1.close();
            resultSet.close();
        }
    }
    public int getIon(String x){
        int ion;
        String [] configs=x.split("-");
        int i=configs.length-1;
        ion=Integer.parseInt(configs[i]);
        return ion;
    }
}
