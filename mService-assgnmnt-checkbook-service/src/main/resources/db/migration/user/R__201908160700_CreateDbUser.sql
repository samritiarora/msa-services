SET @createAppUser = (SELECT IF ((SELECT count(*) FROM mysql.user WHERE user = 'checkbookservice' AND Host ='%') > 0,
                                 "SELECT 'User checkbookservice already exists';",
                                 "CREATE USER 'checkbookservice'@'%' IDENTIFIED BY 'dn2JNuQLlFk6G447BAVDDtx46f0ivwb';"));


PREPARE stmt FROM @createAppUser;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;