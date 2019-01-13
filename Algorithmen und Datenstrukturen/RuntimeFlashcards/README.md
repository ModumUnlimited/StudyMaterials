In a lot of old exams the professors asks for the runtime of some common graph algorithm. These questions are very easy if one knows the exact runtime. However, I have often found myself wondering if Dijkstra runs in `O(|E| + |V|log(|V|))` or if it runs in `O((|E| + |V|)log(|V|))`. The solutions is that if one uses a binary heap with `decreaseKey()` support it is the latter, whereas using a fibonacci heap gives us the first runtime.

As a result, this repo provides you with a PDF composed of flashcards (which I will print and laminate once the PDF is finished). 

Your contributions are very welcome. Feel free to add information to these flashcards. The more stuff we add, the better the flashcards are.


Template configuration file from [https://github.com/figgis/japanese-flashcards/blob/master/avery5371.cfg](https://github.com/figgis/japanese-flashcards/blob/master/avery5371.cfg)