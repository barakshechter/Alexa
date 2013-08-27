CREATE TABLE alexa.Document
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(1024) NOT NULL,
    authorId INT NOT NULL,
    createDate TIMESTAMP NOT NULL,
    modifyDate TIMESTAMP NOT NULL
);

CREATE TABLE alexa.DocumentItem
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    revisionId INT NOT NULL,
    position INT NOT NULL,
    fileId VARCHAR(512) NOT NULL
);

CREATE TABLE alexa.Revision
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    documentId INT NOT NULL,
    date TIMESTAMP NOT NULL,
    comments VARCHAR(4096) NOT NULL,
    createDate TIMESTAMP NOT NULL,
    modifyDate TIMESTAMP NOT NULL
);

CREATE TABLE alexa.Tag
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR(256) NOT NULL,
  description VARCHAR(1024) NOT NULL,
  parentId INT NOT NULL
);

CREATE TABLE FileAttributes
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  fileId TEXT NOT NULL,
  name VARCHAR(64) NOT NULL,
  value TEXT NOT NULL
);

CREATE TABLE alexa.File
(
  id VARCHAR(512) PRIMARY KEY NOT NULL,
  size LONG  NOT NULL,
  path VARCHAR(1024) NOT NULL,
  type VARCHAR(256) NOT NULL
);

CREATE TABLE alexa.MediaFile
(
  id VARCHAR(512) PRIMARY KEY NOT NULL,
  hasAudio BOOLEAN ,
  hasVideo BOOLEAN ,
  duration INT,
  audioCodec VARCHAR(16),
  videoCodec VARCHAR(16),
  audioBitrate INT,
  videoBitrate INT,
  height INT,
  width INT
);