<template>
  <div class="editor-page">
    <div class="header">
      <h1>This is Editor {{ post.postId }}</h1>
      <div class="right">
        <el-button @click="$router.go(-1)">Cancel</el-button>
        <el-button @click="tapSave" type="success">Save</el-button>
      </div>
    </div>
    <div class="content">
      <!-- title -->
      <el-input
        class="m1"
        placeholder="Please Title"
        v-model="post.title"
      ></el-input>

      <el-input
        class="m1"
        placeholder="Please Subtitle"
        v-model="post.subtitle"
      ></el-input>

      <!-- image upload -->
      <div class="m1">
        <div v-if="imageUrl">
          <el-image class="bg-image" :src="imageUrl" alt="" />
          <el-button circle type="danger" icon="el-icon-delete" @click="removeBGImage"></el-button>
        </div>
        <el-upload v-else
          class="upload-demo"
          action=""
          :on-change="addImage"
          :auto-upload="false">
          <el-button slot="trigger">select file</el-button>
        </el-upload>
      </div>

      <!-- quill -->
      <!-- Or manually control the data synchronization -->
      <quill-editor
        class="editor m1"
        :content="post.content"
        :options="editorOption"
        @change="onEditorChange($event)"
      />
    </div>
  </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'
import { upload, reqUpdate } from '@/api/post'
import _ from 'lodash'

export default {
  name: 'Write',
  components: {
    quillEditor
  },
  data() {
    return {
      post: {
        id: 0,
        title: '',
        subtitle: '',
        writter: '',
        content: '',
        backgroundImage: '',
      },

      file: null,

      editorOption: {}
    }
  },
  mounted() {
    console.log('params', this.$route.params)
    this.post = _.clone(this.$route.params)
  },
  computed: {
    postId() {
      const { id } = this.$route.params
      return id
    },
    imageUrl() {
      if (this.file) {
        return URL.createObjectURL(this.file)
      }
      return this.post.backgroundImage
    }
  },
  methods: {
    onEditorChange({ quill, html, text }) {
      console.log('editor change!', quill, html, text)
      this.post.content = html
    },
    async tapSave() {
      try {
        const data = this.file ? await upload(this.file, this.post) : await reqUpdate(this.post)
        this.$alert(`${this.post.title}`, 'Save Ok', {
          confirmButtonText: 'OK',
          callback: (action) => {
            this.$router.replace('/')
          }
        })
      } catch (error) {
        console.error(error)
      }

    },

    removeBGImage() {
      this.post.backgroundImage = ''
      this.file = null
    },
    addImage(file, fileList) {
      this.file = file.raw
    }
  }
}
</script>

<style lang="scss" scoped>
.editor-page {
  padding: 20px;
  margin-right: 3rem;
  display: flex;
  flex-direction: column;
  height: 100%;

  .header {
    display: flex;
    align-items: baseline;
    .right {
      margin-left: auto;
    }
  }
  .content {
    flex: 1;
    display: flex;
    flex-direction: column;
    margin-bottom: 50px;
    .m1 {
      margin: 1rem;
    }
    .bg-image {
      width: 30%;
    }
    .editor {
      flex: 1;
      overflow: auto;
    }
  }
}
</style>
