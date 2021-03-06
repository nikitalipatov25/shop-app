# Use-Case для страницы конкретного товара

Страница товара представляет собой страницу с информацией о конкретном товаре, выбранным покупателем. В верхней части сайта предусмотрены три кнопки "Каталог", "Корзина" и "Личный кабинет", а также строка для поиска товара по каталогу.

Покупатель попадает на страницу с товаром следующими способами:
1. Кликом по карточке товара, которая находится в каталоге или корзине
2. Путём ввода в строку поиска браузера адреса сайта с добавлением адреса страницы каталога (/catalog) и артикула товара (/UUID)

На странице размещена след. информация о товаре:
1. Категория (кликнув на неё, покупатель попадёт в каталог и увидит товары данной категории)
2. Изображение товара
3. Название товара
4. Артикул товара (его первичный ключ)
4. Кол-во товара на складе ("Нет в наличии", если товара нет на складе)
5. Цена товара

По нажатию на кнопку "Добавить в корзину", покупатель попадает в корзину (данная страница описывается в другом документе). При этом, кнопка на странице товара изменит цвет и надпись (зелёный, "В корзине"). Повторное нажатие на кнопку удалит данный товар из корзины.

Также данное действие отображается визуально - в блоке Footer, рядом с конпкой "Корзина" появится значок с цифрой, которая отображает кол-во товара в корзине (увеличить кол-во товара и посмотреть более подробную информацию можно только на странице "Корзина"; при этом кол-во товара в наличии на странице товара и в каталоге уменьшится - товар будет зарезервирован покупателем).

Поиск осущ. аналогично поиску товара по каталогу.