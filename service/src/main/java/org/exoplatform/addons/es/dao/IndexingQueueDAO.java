/* 
* Copyright (C) 2003-2015 eXo Platform SAS.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see http://www.gnu.org/licenses/ .
*/
package org.exoplatform.addons.es.dao;

import org.exoplatform.addons.es.domain.IndexingQueue;
import org.exoplatform.commons.api.persistence.GenericDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 7/29/15
 */
public interface IndexingQueueDAO extends GenericDAO<IndexingQueue, Long> {

  List<IndexingQueue> findQueueFromLastTime(Date lastTime);

  List<IndexingQueue> findQueueBeforeLastTime(Date lastTime);

  List<IndexingQueue> findQueueBeforeLastTimeByOperation(Date lastTime, String operation);

  List<IndexingQueue> findQueueBeforeLastTimeByOperations(Date lastTime, List<String> operations);

  Date getCurrentTimestamp();

}

