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
package org.exoplatform.addons.es.index.elastic;

import org.exoplatform.addons.es.index.IndexingServiceConnector;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.container.xml.PropertiesParam;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 7/22/15
 */
public abstract class ElasticIndexingServiceConnector extends IndexingServiceConnector {

  protected String index;
  protected String mapping;
  protected Integer shards;
  protected Integer replicas;

  private static final Integer DEFAULT_REPLICAS_NUMBER =
      Integer.valueOf((System.getProperty("indexing.replicasNumber.default") != null) ?
      System.getProperty("indexing.replicasNumber.default") : "1");

  private static final Integer DEFAULT_SHARDS_NUMBER =
      Integer.valueOf((System.getProperty("indexing.shardsNumber.default") != null) ?
      System.getProperty("indexing.shardsNumber.default") : "5");

  public ElasticIndexingServiceConnector(InitParams initParams) {
    PropertiesParam param = initParams.getPropertiesParam("constructor.params");
    this.index = param.getProperty("index");
    setType(param.getProperty("type"));
    this.mapping = param.getProperty("mapping");
    this.shards = DEFAULT_SHARDS_NUMBER;
    this.replicas = DEFAULT_REPLICAS_NUMBER;
  }

  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }

  public String getMapping() {
    return mapping;
  }

  public void setMapping(String mapping) {
    this.mapping = mapping;
  }

  public Integer getShards() {
    return shards;
  }

  public void setShards(Integer shards) {
    this.shards = shards;
  }

  public Integer getReplicas() {
    return replicas;
  }

  public void setReplicas(Integer replicas) {
    this.replicas = replicas;
  }
}
