register_google(key = "AIzaSyDtLOs2As_0iDHicZzJhcAde5L5ShDVWS4")
library(leaflet)
library(magrittr)

puntos <- data.frame(lon=c(-3.391830, -3.530613, -3.205903, -3.137145, -0.9015065),
                     lat=c(39.595520, 40.225155, 40.320294, 40.345348, 41.64206),
                     nom=c('Aranjuez', 'San Martin de la vega', 'Ruta de las vegas', 
                           'Mondejar', 'Universidad de Zaragoza, Zaragoza, Espa?a')
)

leaflet(data = puntos) %>% 
    addTiles() %>%
    setView(lng = puntos$lon[1], lat = puntos$lat[1] , zoom = 6) %>%
    addMarkers(~lon, ~lat, popup = ~as.character(nom), label = ~as.character(nom))
