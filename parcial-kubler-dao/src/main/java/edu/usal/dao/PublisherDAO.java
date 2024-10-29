package edu.usal.dao;

import edu.usal.domain.Publisher;
import java.sql.SQLException;

public interface PublisherDAO {

    Publisher getPublisherById(int publisherId) throws SQLException;
}
