<template>
  <div class="custom-list">
    <hr>
    <div class="row">
      <div class="col-3">
        <img
            :src="product.productPhoto"
            style="width: 286px; height: 286px"
            @click="$router.push({ name: 'product-page', params: { id: product.id } })"
        >
      </div>
      <div class="col-3">
        <p>{{product.productName}}</p>
        <p>Стоимость за шт. {{product.productPrice}} руб.</p>
        <p style="color: crimson" v-if="product.productKol === 1"> Поторопитесь! Осталась одна штука</p>
        <p v-else-if="product.productKol === 0"> Товар распродан</p>
        <p v-else> Кол-во на складе: {{product.productKol}} шт.</p>
      </div>
      <div class="col-3">
        <button v-if="product.productKol === 0" class="btn btn-secondary">Добавить в корзину</button>
        <button v-else class="btn btn-primary" @click="addToCart">Добавить в корзину</button>
      </div>
    </div>

  </div>
</template>

<script>
  import { eventBus } from '../main'

  export default {
  props: {
  product: {
  type: Object
}
},
  data() {
  return {
  byDefault: 'list',
  list: false,
  card : true,
}
},
  methods: {
  async addToCart() {


    let token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQG1haWwucnUiLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE2MjYyNzAyNjksImV4cCI6MTYyNjg3NTA2OX0.94eJt0Xxg8ItQ7O9lxVvuTsixFfYlLpL2ipXYx7v7t4'

  await this.$api.cart.addItemToCart(this.product.id, token);
  eventBus.$emit('addToCart');
},
  async deleteFromCart() {
  this.$api.cart.deleteItemFromCart(this.product.id);
  eventBus.$emit('deleteFromCart');
},
}
}
</script>

