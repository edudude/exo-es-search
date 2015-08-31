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
import org.exoplatform.addons.es.index.elastic.ElasticIndexingService;
import org.exoplatform.container.PortalContainer;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 8/20/15
 */
public class IndexingQueueDAOTest extends AbstractDAOTest {

  private IndexingQueueDAO indexingQueueDAO;

  @Before
  public void setUp() {
    PortalContainer container = PortalContainer.getInstance();
    indexingQueueDAO = container.getComponentInstanceOfType(IndexingQueueDAO.class);
  }

  @After
  public void tearDown() {
    indexingQueueDAO.deleteAll();
  }

  @Test
  public void testIndexingQueueCreation() {

    //Given
    List<IndexingQueue> indexingQueues = indexingQueueDAO.findAll();
    Assert.assertEquals(indexingQueues.size(), 0);
    IndexingQueue indexingQueue = new IndexingQueue();
    indexingQueue.setEntityType("blog");
    indexingQueue.setOperation(ElasticIndexingService.INIT);

    //When
    indexingQueueDAO.create(indexingQueue);

    //Then
    Assert.assertEquals(indexingQueueDAO.findAll().size(), 1);
  }

  @Test
  public void testDatabaseAutoGeneratingTimestamp () {

    //Given
    Long startDate = System.currentTimeMillis();
    IndexingQueue indexingQueue = new IndexingQueue();
    indexingQueue.setEntityType("blog");
    indexingQueue.setOperation(ElasticIndexingService.INIT);

    //When
    indexingQueueDAO.create(indexingQueue);

    //Then
    indexingQueue = indexingQueueDAO.find(indexingQueue.getId());
    Assert.assertThat(
        startDate,
        Matchers.lessThanOrEqualTo(indexingQueue.getTimestamp().getTime()));
    Assert.assertThat(
        System.currentTimeMillis(),
        Matchers.greaterThanOrEqualTo(indexingQueue.getTimestamp().getTime()));
  }

}

