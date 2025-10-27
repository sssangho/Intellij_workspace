<template>
  <div>
    <v-row>
      <v-col>
        <h2>사용자 목록</h2>
        <v-btn color="primary" class="mb-4" to="/user/new">
          새 사용자 추가
        </v-btn>
      </v-col>
    </v-row>

    <v-data-table
      :headers="headers"
      :items="users"
      :loading="loading"
    >
      <template #item.actions="{ item }">
        <v-btn
          color="primary"
          size="small"
          :to="`/user/${item.id}/edit`"
        >
          수정
        </v-btn>
        <v-btn
          color="error"
          size="small"
          class="ml-2"
          @click="deleteUser(item.id)"
        >
          삭제
        </v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserList',
  data() {
    return {
      loading: false,
      users: [],
      headers: [
        { title: 'ID', key: 'id' },
        { title: '이름', key: 'name' },
        { title: '이메일', key: 'email' },
        { title: '나이', key: 'age' },
        { title: '작업', key: 'actions', sortable: false }
      ]
    }
  },
  async created() {
    await this.fetchUsers()
  },
  methods: {
    async fetchUsers() {
      this.loading = true
      try {
        const response = await axios.get(`${import.meta.env.VITE_API_URL}/users`)
        this.users = response.data
      } catch (error) {
        console.error('Error fetching users:', error)
      } finally {
        this.loading = false
      }
    },
    async deleteUser(id) {
      if (confirm('정말로 이 사용자를 삭제하시겠습니까?')) {
        try {
          await axios.delete(`${import.meta.env.VITE_API_URL}/users/${id}`)
          await this.fetchUsers()
        } catch (error) {
          console.error('Error deleting user:', error)
        }
      }
    }
  }
}
</script> 