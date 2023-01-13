<template>
  <div class="title">
    <p class="title__message">{{ message }}</p>
    <p class="title__task">
      You've got
      <span class="title__task-total">{{ taskTotal }}</span> tasks today.
    </p>
  </div>
</template>

<script>
export default {
    data() {
        return {
            message: "Hello, Jaewon.",
            taskTotal: 10
        }
    },
}
</script>

<style lang="scss">
.title {
  max-width: $max-width;
  margin: 0 auto;
  letter-spacing: 0.03rem;
  color: #fff;
  &__text {
    cursor: default;
    font-size: 1.6rem;
    @include flexbox;
    @include align-items(center);
    @include flex-wrap(wrap);
    @include ellipsis();
  }
  .wrap {
    position: relative;
    white-space: nowrap;
    overflow: hidden;
  }
  &__message {
    display: block;
    min-height: 2.7rem;
    margin-right: 0.4rem;
    @include flex-shrink(0);
  }
  &__name {
    display: block;
    background: 0;
    border: 0;
    outline: 0;
    color: inherit;
    font-size: inherit;
    min-width: 1rem;
    min-height: 2.7rem;
    overflow: hidden;
    @include flex-shrink(1);
    br {
      display: none;
    }
    &:hover {
      text-decoration: underline;
      cursor: text;
    }
    &:focus {
      opacity: 0.8;
      // outline: 1px solid #fafafa;
      background: rgba(255, 255, 255, 0.15);
      text-decoration: underline;
    }
  }
  &__task {
    margin-top: 3.5rem;
    margin-bottom: 5rem;
    font-weight: bold;
    &-top {
      display: block;
      font-size: 2.6rem;
    }
    &-count {
      display: block;
      font-size: 5.4rem;
    }
    &-total {
      font-size: 50%;
    }
    &-bottom {
      display: block;
      font-size: 2.8rem;
      line-height: 50%;
    }
  }
}
//애니메이션 관리
.title {
  &__text {
    @include animation(fadeShow, 500ms);
  }
  &__task-top {
    @include animation(fadeShow, 600ms, 1, 400ms);
  }
  &__task-count {
    @include animation(fadeShow, 600ms, 1, 550ms);
  }
  &__task-bottom {
    @include animation(fadeShow, 600ms, 1, 700ms);
  }
}
</style>