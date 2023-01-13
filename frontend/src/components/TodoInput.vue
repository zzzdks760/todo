<template>
  <div class="add">
    <input type="text" class="add__input" placeholder="Enter your task" />
    <button class="add__button">Add</button>
  </div>
</template>


<style lang="scss">
.add {
  position: relative;
  max-width: $max-width;
  margin: 0 auto;
  .main-input {
    @include animation(fadeShow, 800ms, 1, 900ms);
  }
}
</style>