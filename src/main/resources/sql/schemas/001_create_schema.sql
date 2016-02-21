####################### update these variables for db versioning purposes ############################################
SET @db_verison_id = '1';
SET @file_name= '001_create_schema';
SET @jira_issue= 'ASSIGNMENT-01';
#######################################################################################################################


CREATE TABLE item (
	id int(11) NOT NULL AUTO_INCREMENT,
	name  VARCHAR(250) NOT NULL ,
	price  DOUBLE NOT NULL,
    stockQuantity INT ,
    category smallint(6) NOT NULL ,
  	PRIMARY KEY (id)
) ENGINE = InnoDB, DEFAULT CHARSET = utf8;

--//@UNDO

DROP TABLE IF EXISTS item;


#######################################################################################################################
INSERT INTO db_version (db_version_id, file_name, jira_issue)
VALUES ( @db_verison_id, @file_name,@jira_issue );
#######################################################################################################################