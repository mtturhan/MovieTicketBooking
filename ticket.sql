CREATE TABLE Tickets (
  id INT AUTO_INCREMENT,
  user_id INT,
  movie VARCHAR(50) NOT NULL,
  seat VARCHAR(10) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES Users(id)
);