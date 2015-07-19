DROP SCHEMA Alexa;
CREATE SCHEMA Alexa;

CREATE TABLE IF NOT EXISTS Alexa.File (
  id         VARCHAR2(128) NOT NULL,
  size       BIGINT,
  createDate TIMESTAMP,
  modifyDate TIMESTAMP,
  type       VARCHAR2(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Alexa.FileTags (
  fileId    VARCHAR2(128) NOT NULL,
  tagId     INTEGER      NOT NULL,
  fileName  VARCHAR2(4096) NOT NULL,
  PRIMARY KEY (fileId, tagId)
);

CREATE TABLE IF NOT EXISTS Alexa.Tag (
  id          INTEGER IDENTITY,
  name        VARCHAR2(4096),
  description TEXT,
  parentId    INTEGER
);

CREATE TABLE IF NOT EXISTS Alexa.FileAttributes (
  id     INTEGER IDENTITY,
  name   VARCHAR2(255),
  value  VARCHAR2(4096),
  fileId VARCHAR2(128)
);

CREATE TABLE IF NOT EXISTS Alexa.MediaFile (
  audioBitrate      INTEGER,
  audioCodec        VARCHAR2(64),
  duration          INTEGER,
  hasAudio          BOOLEAN,
  hasVideo          BOOLEAN,
  height            INTEGER,
  thumbnailId       VARCHAR2(128),
  videoBitrate      INTEGER,
  videoCodec        VARCHAR2(64),
  width             INTEGER,
  id                VARCHAR2(128) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE Alexa.FileAttributes ADD CONSTRAINT FK228E9C13CEF20497 FOREIGN KEY (fileId) REFERENCES Alexa.File;
ALTER TABLE Alexa.FileTags ADD CONSTRAINT FKD802E3D5D250CC02 FOREIGN KEY (tagId) REFERENCES Alexa.Tag;
ALTER TABLE Alexa.FileTags ADD CONSTRAINT FKD802E3D5CEF20497 FOREIGN KEY (fileId) REFERENCES Alexa.File;
ALTER TABLE Alexa.MediaFile ADD CONSTRAINT FKF70067601AE97E1B FOREIGN KEY (id) REFERENCES Alexa.File;
ALTER TABLE Alexa.Tag ADD CONSTRAINT FK1477A11CC1112 FOREIGN KEY (parentId) REFERENCES Alexa.Tag;

INSERT INTO Alexa.Tag (description, name) VALUES ('/','/');