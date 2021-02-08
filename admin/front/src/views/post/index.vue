<template>
  <div class="app-container">

    <!-- header -->
    <div class="header">
      <el-input v-model="keyword" class="mr1" placeholder="Please input" clearable>
        <el-select slot="prepend" v-model="select">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value" />
        </el-select>
        <el-button slot="append" icon="el-icon-search" />
      </el-input>

      <el-button type="primary" icon="el-icon-edit" @click="goEditor">Write</el-button>
    </div>

    <!-- list -->
    <el-table v-loading="listLoading" :data="list" @row-click="handleRowClick">
      <el-table-column prop="id" label="번호" width="100" />
      <el-table-column prop="title" label="제목" />
      <el-table-column prop="subtitle" label="부제목" />

      <!-- slot-scope example -->
      <el-table-column prop="writter" label="작성자" width="100">
        <template slot-scope="scope">
          <el-tag> {{ scope.row.writter }} </el-tag>
        </template>
      </el-table-column>

      <!-- formatter example -->
      <el-table-column prop="updated" label="작성일" width="200" :formatter="dateString" />

    </el-table>
  </div>
</template>

<script>
import dayjs from 'dayjs'
import { getList } from '@/api/post'
import _ from 'lodash'

export default {
  name: 'Post',
  data() {
    return {
      select: 'title',
      options: [
        { value: 'title', label: '제목' },
        { value: 'subtitle', label: '부제목' },
        { value: 'writter', label: '작성자' }
      ],
      keyword: '',

      tableData: [],
      listLoading: false
    }
  },
  computed: {
    list() {
      if (!this.keyword || !this.select) {
        return this.tableData
      }
      const listData = _.filter(this.tableData, (p) => {
        return _.includes(this.selectedValue(p), this.keyword)
      })
      return listData
    }
  },
  mounted() {
    this.fetchData()
  },
  methods: {
    selectedValue(post) {
      const { title, subtitle, writter } = post
      switch (this.select) {
        case 'subtitle': return subtitle
        case 'writter': return writter
        default: return title
      }
    },
    dateString(row, column) {
      return dayjs(row.updated).format('YYYY년 MM월 DD일 HH시 mm분')
    },
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        this.tableData = response.data.list.content
        this.listLoading = false
      })
    },
    handleRowClick(row, column, event) {
      this.goEditor({ ...row })
    },
    goEditor(params) {
      this.$router.push({ name: 'Write', params })
    }
  }
}
</script>

<style lang="scss" scoped>
.el-select {
  width: 110px;
}
.header {
  display: flex;

  .mr1 {
    margin-right: 1rem;
  }

  .right {
    margin-left: auto;
  }
}
</style>
