import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/post',
    method: 'get',
    params
  })
}

export function reqUpdate(value) {
    console.log('update', value)
    return request({
      url: '/post',
      method: 'POST',
      data: value
    })
  }
  
  export function upload(file, content) {
    const formData = new FormData()
    formData.append('file', file)
    for (var key in content) {
      formData.append(key, content[key])
    }
  
    return request({
      url: '/post/upload',
      method: 'POST',
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }
  