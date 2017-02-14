Using the MaSyMoS-Morre API
===========================

The MaSyMoS-Morre client library features two interfaces: `Morre` to query the rank/retrieve engine and `MorreCrawlerInterface`
to push, delete and list models in the database.

Query MaSyMoS-Morre
-------------------

Morre supports a variety of queries on different indexes, as well as a simple query interface.
All can be accessed via the `Morre` interface and the HTTP client implementation.

```java
import de.unirostock.sems.morre.client.impl.HttpMorreClient;
import de.unirostock.sems.morre.client.Morre;

Morre morre = new HttpMorreClient( "http://example.org:7474/morre/" );
```

### Simple Queries
```java
import de.unirostock.sems.morre.client.dataholder.ModelResult;

List<ModelResult> result = morre.modelQuery("novak");
```

### Advanced Queries

More advanced queries in Morre, may feature different features. To determine, which features may be used `getQueryFeatures` can be used.

```java
import de.unirostock.sems.morre.client.QueryType;

String[] queries = { QueryType.ANNOTATION_MODEL_QUERY, QueryType.ANNOTATION_QUERY, QueryType.CELLML_MODEL_QUERY,
        QueryType.MODEL_QUERY, QueryType.PERSON_MODEL_QUERY, QueryType.PERSON_QUERY, QueryType.PUBLICATION_MODEL_QUERY,
        QueryType.PUBLICATION_QUERY, QueryType.SIMPLE_CELLML_MODEL_QUERY };
		
for( int i = 0; i < queries.length; i++ ) {
  System.out.println( queries[i] + ":" );
  List<String> features = morre.getQueryFeatures(queries[i])
  System.out.println( features );
}
```

Once determined, these features can than be used to construct complex queries.

```java
import de.unirostock.sems.morre.client.FeatureSet;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.dataholder.PersonResult;

FeatureSet features = new FeatureSet();
features.set("FAMILYNAME", "Lloyd");

// search for models
List<ModelResult> modelResult = morre.doModelQuery(QueryType.PERSON_MODEL_QUERY, features);
// search for persons
List<PersonResult> personResult = doPersonQuery(features);
```

Push models to MaSyMoS-Morre
----------------------------

To push models to Morre it is neccessary to address a different interface. This interface also allows to query for models and
versions of models based on a `fileID` and `versionID`, which are artificial identifier used to identify a collection of versions of the same model (only `fileID`) or a specific version of a model (`fileID` and `versionID`).
The information returned about a model are stored within a `CrawledModel` dataholder, which contains meta information.

```java
import de.unirostock.sems.morre.client.impl.HttpMorreClient;
import de.unirostock.sems.morre.client.MorreCrawlerInterface;

MorreCrawlerInterface morre = new HttpMorreClient( "http://example.org:7474/morre/" );
```

### Get all versions of a model
```java
List<String> versionIds = morre.getModelHistory("fileID");
```

### Get one version of a model
```java
import de.unirostock.sems.morre.client.dataholder.CrawledModel;

CrawledModel model;

// get one specific version
model = morre.getModelVersion("fileID", "version-2");
// get the latest version
model = morre.getLatestModelVersion("fileID");
```

### Push a model to MaSyMoS

To push a model to MaSyMoS the `CrawledModel` dataholder must be populated with all available/relevant meta information.
However mandatory fields are `fileId`, `versionId`, and `xmldoc`. Last one needs to contain a HTTP URL, pointing to the model file.
The `parentMap` contains all direct predecessors of the pushed model, in the format `fileID1 -> [versionID1, versionID2], ...`.

```java
import de.unirostock.sems.morre.client.dataholder.CrawledModel;

Map<String, List<String>> parentMap = new HashMap<>();
parentMap.put("fileID", Arrays.asList("version-2"));

CrawledModel model = new CrawledModel("fileID", "version-3", "http://example.org/model/version3.xml", parentMap, null, CrawledModel.TYPE_SBML);
```
