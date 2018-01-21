# GifSearcher
Gif Searcher for Qulix Systems

То, что успел. + Уже работает. 

(Ниже, довожу до рабочего состояния, после дедлайна 😕 )
## UPD:
- implement SearchView
- prepare Retrofit + RX service
- add trending endpoint, add search endpoint

## screenshoots

Example trending:

<img alt="screen diffutil demo" src="/images/trending.png" />

Example search:

<img alt="screen diffutil demo" src="/images/search.png" />

## TODO
- поддержка сохранения состояния при смене конфигурации (например, поместить gif'ки в ViewModel Android Architecture Components)
- запрос данных через RxJava
- поддержка пагинации (постепенная подгрузка gif'ок)
- разные layout'ы при портретной и альбомной ориентации (например, при альбомной увеличивать span GridLayout на 1)
- ItemClickListener / разворачивать картинку на весь экран
