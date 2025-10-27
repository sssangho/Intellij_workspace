<template>
  <div>
    <v-row>
      <v-col>
        <h2>{{ isEdit ? '사용자 수정' : '새 사용자 추가' }}</h2>
      </v-col>
    </v-row>

    <v-form @submit.prevent="saveUser" ref="form">
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="user.name"
            label="이름"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="user.email"
            label="이메일"
            type="email"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model.number="user.age"
            label="나이"
            type="number"
            required
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-btn color="primary" type="submit" :loading="loading">
            저장
          </v-btn>
          <v-btn class="ml-2" to="/">
            취소
          </v-btn>
        </v-col>
      </v-row>
    </v-form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserForm',
  data() {
    return {
      loading: false,
      user: {
        name: '',
        email: '',
        age: null
      }
    }
  },
  computed: {
    isEdit() {
      return !!this.$route.params.id
    }
  },
  async created() {
    if (this.isEdit) {
      await this.fetchUser()
    }
  },
  methods: {
    async fetchUser() {
      this.loading = true
      try {
        const response = await axios.get(`http://localhost:9110/api/users/${this.$route.params.id}`)
        this.user = response.data
      } catch (error) {
        console.error('Error fetching user:', error)
      } finally {
        this.loading = false
      }
    },
    async saveUser() {
      this.loading = true
      try {
        if (this.isEdit) {
          await axios.put(`http://localhost:9110/api/users/${this.$route.params.id}`, this.user)
        } else {
          await axios.post('http://localhost:9110/api/users', this.user)
        }
        this.$router.push('/')
      } catch (error) {
        console.error('Error saving user:', error)
      } finally {
        this.loading = false
      }
    }
  }
}
</script> 