library(SPARQL) # SPARQL querying package
library(ggplot2)

endpoint <- "https://query.wikidata.org/sparql"
query <- 'select ?municipio ?municipioLabel ?lonlat ?codipostal ?website where {\n       ?municipio wdt:P131 wd:Q12600.\n       ?municipio wdt:P625 ?lonlat.\n       ?municipio wdt:P281 ?codipostal.\n       ?municipio wdt:P856 ?website.          \n  SERVICE wikibase:label { bd:serviceParam wikibase:language "ca". }\n     }\n'
useragent <- paste("WDQS-Example", R.version.string)  

qd <- SPARQL(endpoint,query,curl_args=list(useragent=useragent))
df <- qd$results
