import {BACKEND_URL} from '../../constants/Metadata';

class UploadMultimediaController {
  constructor() {
    this.uploading = false;
    this.endPoint = `${BACKEND_URL}/api/multimedia`;
    this.fileName = null;
  }

  onUpload(files) {
    this.uploading = true;
    this.fileName = angular.copy(files.item(0).name);
    return files;
  }

  onSuccess(response) {
    this.media.mediaId = response.data.mediaId;
    this.media.url = response.data.url;
    return response;
  }

  onError(response) {
    return response;
  }

  onComplete(response) {
    this.uploading = false;
    return response;
  }
}

export const UploadMultimedia = {
  template: require('./UploadMultimedia.html'),
  controller: UploadMultimediaController,
  bindings: {
    media: '=',
    type: '@'
  }
};
