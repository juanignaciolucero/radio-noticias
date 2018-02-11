import {MULTIMEDIA_URL, BACKEND_URL} from '../../constants/Metadata';

class UploadMultimediaController {
  constructor($log) {
    this.uploading = false;
    this.endPoint = `${BACKEND_URL}/api/multimedia`;
    this.imageUrl = '';
    if (this.mediaId) {
      this.imageUrl = this.getMultimediaUrl(this.mediaId, this.type, this.extension);
    }
    this.fileName = null;
    $log.info(this);
  }

  onUpload(files) {
    this.uploading = true;
    this.fileName = angular.copy(files.item(0).name);
    return files;
  }

  getMultimediaUrl(mediaId, type, extension) {
    return `${MULTIMEDIA_URL}/${type}/${mediaId.substring(0, 4)}/${mediaId}.${extension}`;
  }

  onSuccess(response) {
    this.mediaId = response.data.mediaId;
    if (this.type === 'IMAGE') {
      this.imageUrl = response.data.url;
    }
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
    extension: '@',
    fileName: '@',
    mediaId: '=',
    type: '@'
  }
};
