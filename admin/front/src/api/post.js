import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/post',
    method: 'get',
    params
  })
}
